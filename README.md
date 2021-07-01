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
open xcode
create folder Frameworks
copy jitsimeetsdk.xcframeworks to folder Frameworks
general frameworks, lib -> embed & sign
build phases -> link binary with libraries -> check framework required

flutter run
