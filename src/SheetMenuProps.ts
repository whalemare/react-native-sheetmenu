import { ActionItem } from './ActionItem'

export interface SheetMenuProps {
  title: string
  actions: [ActionItem]
  ios?: { 
    cancelButtonIndex?: number
    destructiveButtonIndex?: number
  }
}