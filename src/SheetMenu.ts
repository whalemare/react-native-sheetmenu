import { ISheetMenu } from './ISheetMenu';
import React, { PureComponent } from 'react'
import { NativeModules, Platform } from 'react-native'
import { SheetMenuProps } from './SheetMenuProps';

const { NativeLibrary } = NativeModules

export class SheetMenu implements ISheetMenu {
  props: SheetMenuProps

  constructor(props: SheetMenuProps) {
    this.props = props;
  }

  show(): void {
    NativeLibrary.show(this.props)
  } 
  
  close(): void {
    throw new Error("Method not implemented.")
  }
}
