import UIKit
import JitsiMeetSDK

class SecondController: UIViewController {
    
//    @IBOutlet weak var videoButton: UIButton?
//    @IBOutlet weak var roomName: UITextField!
    @IBOutlet var videoButton: UIButton!
    @IBOutlet var roomName: UITextField!
    @IBOutlet var backButton: UIButton!
    
    fileprivate var jitsiMeetView: JitsiMeetView?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let defaultOptions = JitsiMeetConferenceOptions.fromBuilder { (builder) in
            // for JaaS replace url with https://8x8.vc
            builder.serverURL = URL(string: "https://meet.jit.si")
            // for JaaS use the obtained Jitsi JWT
            // builder.token = "SampleJWT"
            builder.welcomePageEnabled = false
            // Set different feature flags
            builder.setFeatureFlag("toolbox.enabled", withBoolean: false)
            builder.setFeatureFlag("filmstrip.enabled", withBoolean: false)
        }
        
        JitsiMeet.sharedInstance().defaultConferenceOptions = defaultOptions
    }
    
    @IBAction func openJitsiMeet(sender: Any?) {
//        let room: String = roomName.text!
//        if(room.count < 1) {
//            return
//        }
        
        // create and configure jitsimeet view
        let jitsiMeetView = JitsiMeetView()
        jitsiMeetView.delegate = self
        self.jitsiMeetView = jitsiMeetView
        let options = JitsiMeetConferenceOptions.fromBuilder { (builder) in
            // for JaaS use <tenant>/<roomName> format
            builder.room = "123123"
            // Settings for audio and video
            // builder.audioMuted = true;
            // builder.videoMuted = true;
        }
                
        // setup view controller
        let vc = UIViewController()
        vc.modalPresentationStyle = .fullScreen
        vc.view = jitsiMeetView
        
        // join room and display jitsi-call
        jitsiMeetView.join(options)
        present(vc, animated: true, completion: nil)
        
    }
    
    fileprivate func cleanUp() {
        if(jitsiMeetView != nil) {
            dismiss(animated: true, completion: nil)
            jitsiMeetView = nil
        }
    }
    @IBAction func backScreen(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
}

extension SecondController: JitsiMeetViewDelegate {
    func conferenceTerminated(_ data: [AnyHashable : Any]!) {
        cleanUp()
    }
}
