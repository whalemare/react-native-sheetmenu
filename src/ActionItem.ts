export interface ActionItem {
  title: string

  /**
   * Function that should be called when the action is clicked
   */
  onPress?: () => void

  /**
   * Icon name without extension for *android only*
   * You should put your icons into _mipmap_ or _drawable_ folder
   */
  iconName?: string
}