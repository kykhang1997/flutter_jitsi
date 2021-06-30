clone project jitsi meet

open project -> react -> Future -> change UI

!Android

create folder tmp/repo
open terminal vscode

- cp -r node_modules/react-native/android/com tmp/repo/
- cp -r node_modules/jsc-android/dist/org tmp/repo/
- ./android/scripts/release-sdk.sh /tmp/repo
  copy file tmp add folder android project
  open file android/build.gradle add - allprojects {
  repositories {
  google()
  jcenter()
  maven {url "file:/tmp/repo"} !add this
  }
  }
  open file android/app/build.gradle add in dependencies - implementation ('org.jitsi.react:jitsi-meet-sdk:+') { transitive = true }
  open file android/app/main/res/kotlin to code native android

!ios
open terminal
run ./ios/scripts/release-sdk.sh
copy workspace and sdk to project ios
add podfile - target 'JitsiMeetSDK' do
project 'sdk/sdk.xcodeproj'
end - platform :ios, '11.0'
run pod install
open runner/appdelegate.swift to code ios swift

flutter run
