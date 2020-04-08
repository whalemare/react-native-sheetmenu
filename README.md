# react-native-sheetmenu
Fully native implementation of actions menu for iOS and Android following by system design and guidelines.

Install
-------

```bash
yarn add react-native-sheetmenu
```

Usage
-----

```ts
import SheetMenu from 'react-native-sheetmenu'

SheetMenu.show({
  title: "Hello from React Native",
  actions: [{ 
    title: "Menu item title",
    onPress: () => console.log("Pressed on me!"),
  }]
})
```

Roadmap
-------

If you need some custom features, please open issue with enhancement description.