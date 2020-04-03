export type ActionStyle = "cancel" | "destructive"

export interface ActionItem {
  /**
   * Name of your action, that should be visible for user
   */
  title: string

  /**
   * Function that should be called when the action is clicked
   */
  onPress?: () => void

  style?: ActionStyle

  /**
   * Android specific properties
   */
  android?: {
    /**
     * Icon name without extension for *android only*
     * You should put your icons into _mipmap_ or _drawable_ folder
     */
    iconName?: string
  }
}