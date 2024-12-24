import { NativeModule, requireNativeModule } from "expo";

declare class BlurAppInRecentsModule extends NativeModule {
  enable(): void;
  disable(): void;
  isBlurringEnabled(): boolean;
}

export default requireNativeModule<BlurAppInRecentsModule>("BlurAppInRecents");
