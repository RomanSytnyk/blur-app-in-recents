//
//  AppLifecycleDelegate.swift
//
//  Created by Roman Sytnyk on 24.12.2024.
//

import ExpoModulesCore

public class AppLifecycleDelegate: ExpoAppDelegateSubscriber {
    private static var _isBlurringEnabled: Bool = false
    private static var onBlurringEnabledChanged: ((Bool) -> Void)?
    
    public static var isBlurringEnabled: Bool {
        get { _isBlurringEnabled }
        set {
            _isBlurringEnabled = newValue
            onBlurringEnabledChanged?(newValue)
        }
    }
    
    func applyBlurEffect(_ application: UIApplication) {
        guard AppLifecycleDelegate.isBlurringEnabled,
              let window = application.delegate?.window else { return }

        let blurEffect = UIBlurEffect(style: .light)
        let blurEffectView = UIVisualEffectView(effect: blurEffect)
        blurEffectView.frame = window!.bounds
        blurEffectView.tag = 71297
        window!.addSubview(blurEffectView)
    }

    func removeBlurEffect(_ application: UIApplication) {
        guard let window = application.delegate?.window else { return }

        if let blurEffectView = window!.viewWithTag(71297) {
            blurEffectView.removeFromSuperview()
        }
    }

    public func applicationDidBecomeActive(_ application: UIApplication) {
        removeBlurEffect(application)
    }

    public func applicationWillResignActive(_ application: UIApplication) {
        if AppLifecycleDelegate.isBlurringEnabled {
            applyBlurEffect(application)
        }
    }
}
