import * as coreModules from '../modules/core.js';
import TableRegistry from './TableRegistry.js';

export default class ModuleBinder {

	constructor(tabulator, modules){
		this.bindStaticFuctionality(tabulator);
		this.bindModules(tabulator, coreModules, true);

		if(modules){
			this.bindModules(tabulator, modules);
		}
	}

	bindStaticFuctionality(tabulator){
		tabulator.moduleBindings = {};

		tabulator.extendModule = function(name, property, values){
			if(tabulator.moduleBindings[name]){
				var source = tabulator.moduleBindings[name][property];

				if(source){
					if(typeof values == "object"){
						for(let key in values){
							source[key] = values[key];
						}
					}else{
						console.warn("Module Error - Invalid value type, it must be an object");
					}
				}else{
					console.warn("Module Error - property does not exist:", property);
				}
			}else{
				console.warn("Module Error - module does not exist:", name);
			}
		};

		tabulator.registerModule = function(modules){
			if(!Array.isArray(modules)){
				modules = [modules];
			}

			modules.forEach((mod) => {
				tabulator.registerModuleBinding(mod)
			});
		}

		tabulator.registerModuleBinding = function(mod){
			tabulator.moduleBindings[mod.moduleName] = mod;
		};

		tabulator.findTable = function(query){
			var results = TableRegistry.lookupTable(query, true);
			return Array.isArray(results) && !results.length ? false : results;
		}

		//ensure that module are bound to instantiated function
		tabulator.prototype.bindModules = function(){
			var orderedMods = [],
			unOrderedMods = [];

			this.modules = {};

			for(var name in tabulator.moduleBindings){
				let mod = tabulator.moduleBindings[name];
				let module = new mod(this);

				this.modules[name] = module;

				if(mod.prototype.moduleCore){
					this.modulesCore.push(module);
				}else{
					if(mod.moduleInitOrder){
						orderedMods.push(module);
					}else{
						unOrderedMods.push(module);
					}
				}
			}

			orderedMods.sort((a, b) => a.moduleInitOrder > b.moduleInitOrder ? 1 : -1);

			this.modulesRegular = orderedMods.concat(unOrderedMods);
		}
	}

	bindModules(tabulator, modules, core){
		var mods = Object.values(modules);

		if(core){
			mods.forEach((mod) => {
				mod.prototype.moduleCore = true;
			});
		}

		tabulator.registerModule(mods);
	}
}