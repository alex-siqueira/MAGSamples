//
//  LoginViewController.swift
//  magsample
//
//  Created by Alexandre Siqueira on 09/06/17.
//  Copyright © 2017 Alexandre Siqueira. All rights reserved.
//

import Foundation
import UIKit
import MASFoundation

class LoginViewController: UIViewController {
    
    @IBOutlet weak var txtLogin: UITextField!
    
    @IBOutlet weak var txtPassword: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func login(_ sender: UIButton) {
        MASUser.login(withUserName: txtLogin.text!, password: txtPassword.text!) { (completed, error) in
            if (error != nil){
                print ("erro ao logar")
                print (error?.localizedDescription)
            } else {
                self.dismiss(animated: true, completion: {
                    print ("indo para tela principal")
                })
            }
        }
    }
}
