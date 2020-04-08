import { ActionSheetIOS, ActionSheetIOSOptions, NativeModules, Platform } from 'react-native'
import { SheetMenuProps } from './SheetMenuProps'

const { NativeLibrary } = NativeModules

export class SheetMenu {
  private props: SheetMenuProps

  constructor(props: SheetMenuProps) {
    this.props = props
  }

  /**
   * Create and show menu for user interactions
   */
  static show(props: SheetMenuProps) {
    const sheetMenu = new SheetMenu(props)
    sheetMenu.show()
    return sheetMenu
  }

  /**
   * Show menu for user interactions
   */
  show(): void {
    if (Platform.OS === 'android') {
      NativeLibrary.show(this.props, this.callback)
    } else {
      const options = this.mapPropsToOptions(this.props)
      ActionSheetIOS.showActionSheetWithOptions(options, this.callback)
    }
  }

  /**
   * Close all active menus
   */
  close(): void {
    NativeLibrary.close()
  }

  private callback = (index: number) => {
    const action = this.props.actions[index]
    if (action.onPress) {
      action.onPress()
    }
  }

  private mapPropsToOptions(props: SheetMenuProps): ActionSheetIOSOptions {
    const options: ActionSheetIOSOptions = {
      options: props.actions.map(action => action.title),
      title: props.title,
      cancelButtonIndex: props.actions.findIndex(element => element.style === 'cancel'),
      destructiveButtonIndex: props.actions.findIndex(element => element.style === 'destructive'),
    }
    return options
  }
}
