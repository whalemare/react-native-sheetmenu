import { ISheetMenu } from './ISheetMenu';
import React, { PureComponent } from 'react'
import { NativeModules, Platform, ActionSheetIOS, ActionSheetIOSOptions } from 'react-native'
import { SheetMenuProps } from './SheetMenuProps';
import { ActionItem, ActionStyle } from './ActionItem';

const { NativeLibrary } = NativeModules

export class SheetMenu implements ISheetMenu {
  props: SheetMenuProps

  constructor(props: SheetMenuProps) {
    this.props = props;
  }

  callback = (index: number) => {
    const action = this.props.actions[index]
    if (action.onPress) {
      action.onPress()
    }
  }

  show(): void {
    if (Platform.OS === 'android') {
      NativeLibrary.show(this.props, this.callback)
    } else {
      const options = this.mapPropsToOptions(this.props)
      ActionSheetIOS.showActionSheetWithOptions(options, this.callback)
    }
  } 

  mapPropsToOptions(props: SheetMenuProps): ActionSheetIOSOptions {
    const options: ActionSheetIOSOptions = {
      options: props.actions.map(action => action.title),
      title: props.title,
      cancelButtonIndex: props.actions.findIndex(element => element.style === "cancel"),
      destructiveButtonIndex: props.actions.findIndex(element => element.style === "destructive"),
    }
    return options
  }
  
  close(): void {
    throw new Error("Method not implemented.")
  }
}
