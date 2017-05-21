//
//  AppDelegate.swift
//  magsample
//
//  Created by Alexandre Siqueira on 20/05/17.
//  Copyright © 2017 Alexandre Siqueira. All rights reserved.
//

import UIKit
import MASFoundation

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?


    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool {
        
        MAS.setGrantFlow(MASGrantFlow.password)
        
        MAS.start { (completed, error) in
            if (error != nil){
                print(error!.localizedDescription)
            } else if (completed) {
                print("MAS Started!")
            }
        }
        
        return true
    }

    func applicationWillResignActive(_ application: UIApplication) {
    }

    func applicationDidEnterBackground(_ application: UIApplication) {
    }

    func applicationWillEnterForeground(_ application: UIApplication) {
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
    }

    func applicationWillTerminate(_ application: UIApplication) {
    }


}

