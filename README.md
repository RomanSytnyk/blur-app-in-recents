
# Blur App In Recents

![Platform - Android and iOS](https://img.shields.io/badge/platform-android%20%26%20ios-bg)
[![NPM Badge](https://img.shields.io/npm/v/%40roman.sytnyk%2Fblur-app-in-recents)](https://www.npmjs.com/package/%40roman.sytnyk%2Fblur-app-in-recents)

A React Native module for Expo that prevents sensitive content from being visible in the app in Recents menu of iOS & Android. 

Thia package can be useful for banking, finance, and other security-focused applications.

The key difference with [Expo Screen Capture](https://docs.expo.dev/versions/latest/sdk/screen-capture/) is that this package allows taking screenshots when the app is in the foreground, blurring content only in the background state.

## Installation

You can install this package using npm or yarn:

```npm install @roman.sytnyk/blur-app-in-recents```

or

```yarn add @roman.sytnyk/blur-app-in-recents```

Also, don't forget make new prebuild to apply native code

```npx expo prebuild```

## Usage
```
import BlurAppInRecents from '@roman.sytnyk/blur-app-in-recents';

React.useEffect(() => {
  BlurAppInRecents.enable();
}, []);
```

## Screenshots
![iOS Screenshot](https://i.ibb.co/WPfT696/Simulator-Screenshot-i-Phone-16-Pro-2024-12-24-at-11-07-48.png) 
![Android Screenshot](https://i.ibb.co/fqZjbfq/Screenshot-20241224-141343.png)

## API

### `BlurAppInRecents`

- **`enable`**: Activates the blur effect when the app goes to the Recents menu.
- **`disable`**: Deactivates the blur effect.

## Limitations
For Android devices running below Android 13, the blur effect may not work as expected due to platform limitations. 

Specifically, blurring doesn't have any effects on devices with Android versions lower than 13, where gesture navigation is enabled instead of bottom buttons. 

It's a system bug, which you can find more details about on [Google's Issue Tracker](https://issuetracker.google.com/issues/123205795)

## Contributing

Please submit a pull request or open an issue to discuss what you would like to change. Maintainers are welcomed.