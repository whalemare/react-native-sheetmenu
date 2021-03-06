/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, { Fragment } from 'react'
import {
  SafeAreaView,
  StyleSheet,
  ScrollView,
  View,
  Text,
  StatusBar,
  TouchableOpacity,
  Alert,
  Switch,
} from 'react-native'

import {
  Header,
  LearnMoreLinks,
  Colors,
  DebugInstructions,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen'

import SheetMenu from 'react-native-sheetmenu'

const Button = props => {
  return (
    <TouchableOpacity
      style={{
        height: 100,
        backgroundColor: '#f4f4f4',
        alignItems: 'center',
      }}
      onPress={props.onPress}>
      <Text>{props.text}</Text>
    </TouchableOpacity>
  )
}

const App = () => {
  return (
    <View>
      <StatusBar barStyle="dark-content" />
      <SafeAreaView>
        <Button
          text={'SheetMenu.show()'}
          onPress={async () => {
            SheetMenu.show({
              title: 'Hello from React Native',
              actions: [
                {
                  title: 'Do',
                  onPress: () => {
                    Alert.alert('Clicked Do')
                  },
                  android: {
                    iconName: 'ic_check',
                  },
                },
                {
                  title: 'Something',
                  android: {
                    iconName: 'ic_check',
                  },
                  onPress: () => {
                    Alert.alert('Clicked Something')
                  },
                },
                {
                  android: {
                    iconName: 'ic_check',
                  },
                  onPress: () => {
                    Alert.alert('Clicked Nice')
                  },
                },
              ],
            })
          }}
        />
      </SafeAreaView>
    </View>
  )
}

const styles = StyleSheet.create({
  scrollView: {
    backgroundColor: Colors.lighter,
  },
  engine: {
    position: 'absolute',
    right: 0,
  },
  body: {
    backgroundColor: Colors.white,
  },
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
    color: Colors.black,
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
    color: Colors.dark,
  },
  highlight: {
    fontWeight: '700',
  },
  footer: {
    color: Colors.dark,
    fontSize: 12,
    fontWeight: '600',
    padding: 4,
    paddingRight: 12,
    textAlign: 'right',
  },
})

export default App
