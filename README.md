Copyright 2016 - The CyanogenMod Project

Device configuration for Samsung Galaxy j3 (Qualcomm variants)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<manifest>
    <!-- Remove Telecomm -->
    <remove-project name="CyanogenMod/android_packages_services_Telecomm" />

    <!-- Remove Qcom FM -->
    <remove-project name="CyanogenMod/android_hardware_qcom_fm" />


    <project path="device/samsung/j3ltespr" name="Hooks405/android_device_j3ltespr" revision="cm-13.0" />

    <!-- Kernels -->
    <project path="kernel/samsung/j3ltespr" name="Grace5921/kernel_samsung_j3ltespr" revision="cm-12.1" />


    <!-- Vendor -->
    <project path="vendor/samsung" name="MSM8916-Samsung/proprietary_vendor_samsung" revision="cm-13.0" />

    <!-- Common device repos -->
    <project path="device/samsung/qcom-common" name="CyanogenMod/android_device_samsung_qcom-common" revision="cm-13.0" />
    <project path="device/qcom/common" name="CyanogenMod/android_device_qcom_common" revision="cm-13.0" />

    <!-- Hardware 
    <project path="hardware/qcom/fm" name="CyanogenMod/android_hardware_qcom_fm" revision="cm-13.0" />-->
    <project path="hardware/samsung" name="CyanogenMod/android_hardware_samsung" revision="cm-13.0" />

    <!-- Other -->
    <project path="external/sony/boringssl-compat" name="CyanogenMod/android_external_sony_boringssl-compat" revision="cm-13.0" />
    <project path="external/stlport" name="CyanogenMod/android_external_stlport" revision="cm-13.0" />

    <!-- CM replacements -->
    <project path="packages/services/Telecomm" name="TheWhisp/android_packages_services_Telecomm" revision="cm-13.0" />
    <project path="hardware/qcom/fm" name="TheWhisp/android_hardware_qcom_fm" revision="cm-13.0" />
</manifest>

```
