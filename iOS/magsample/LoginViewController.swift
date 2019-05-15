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
import MASUI

class LoginViewController: MASBaseLoginViewController {
    
    @IBOutlet weak var txtLogin: UITextField!
    
    @IBOutlet weak var txtPassword: UITextField!
    
    override func viewWillReload() {
        super.viewWillReload()
    }
    
    override func viewDidReload() {
        super.viewDidReload()
    }
    
    override func cancel() {
        super.cancel()
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
