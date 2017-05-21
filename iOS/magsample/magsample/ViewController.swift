//
//  ViewController.swift
//  magsample
//
//  Created by Alexandre Siqueira on 20/05/17.
//  Copyright © 2017 Alexandre Siqueira. All rights reserved.
//

import UIKit
import MASFoundation

class ViewController: UIViewController {
    @IBOutlet weak var mainText: UITextView!

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func go(_ sender: UIButton) {
        MAS.getFrom("/gpm/servicos", withParameters: nil, andHeaders: nil, request: MASRequestResponseType.unknown, responseType: MASRequestResponseType.json) { (responseInfo, error) in
            if (error != nil){
                print (error!.localizedDescription)
            } else {
                self.mainText.text = responseInfo?.description
            }
        
        }
        
    }

    @IBAction func logout(_ sender: UIButton) {
        var user: MASUser
        user = MASUser.current()!

        print(user.isAuthenticated)
        user.logout { (completed, error) in
            if (error != nil){
                print (error!.localizedDescription)
            } else if (completed){
                print ("logged off")
            }
        }
    }
}

