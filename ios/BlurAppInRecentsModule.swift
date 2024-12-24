import ExpoModulesCore

public class BlurAppInRecentsModule: Module {
    public func definition() -> ModuleDefinition {
        Name("BlurAppInRecents")
        
        Function("enable") {
            AppLifecycleDelegate.isBlurringEnabled = true
        }

        Function("disable") {
            AppLifecycleDelegate.isBlurringEnabled = false
        }
        
        Function("isBlurringEnabled") { () -> Bool in
            return AppLifecycleDelegate.isBlurringEnabled
        }
    }
}
