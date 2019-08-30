# react-native-sheetmenu
Library for speedy implementation bottom menu with platform specific design.

For android used [sheetmenu](https://github.com/whalemare/sheetmenu) native implementation

For ios used [ActionSheetIOS]() native implementation

Install
-------

```bash
npm install react-native-sheetmenu --save
```

Usage
-----

Just pass list of `actions` and call `.show()` method

```js
import SheetMenu from 'react-native-sheetmenu'

class App extends React.Component {

  render() {
    return (
      <View>
        <Text onPress={() => {
          const sheetMenu = new SheetMenu({
            title: "Hello from React Native",
            actions: [{ 
              title: "Menu item title",
              onPress: () => Alert.alert("Item pressed"),
              iconName: "android_icon_name_in_drawable", // not required
            }]
          })
          sheetMenu.show()
        }}>
          Open SheetMenu
        </Text>
      </View>
    )
  }
}
```

Roadmap
-------

If you need some custom features, please open issue with enhancement description.