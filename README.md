Copyright 2016 - The CyanogenMod Project

Device configuration for Samsung Galaxy j3 (Qualcomm variants)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<manifest>
    <!-- Remove Telecomm -->
    <remove-project name="CyanogenMod/android_packages_services_Telecomm" />

    <!-- Device repos -->
    <!-- A series -->
    <project path="device/samsung/j3ltespr" name="Hooks405/android_device_j3ltespr" revision="cm-13.0" />

    <!-- Kernels -->
    <project path="kernel/samsung/j3ltespr" name="Hooks405/kernel_samsung_j3ltespr" revision="cm-13.0" />

    <!-- Vendor -->
    <!--<project path="vendor/samsung" name="MSM8916-Samsung/proprietary_vendor_samsung" revision="cm-12.1" />-->

    <!-- Common device repos -->
    <project path="device/samsung/qcom-common" name="TheWhisp/android_device_samsung_qcom-common" revision="cm-12.1" />
    <project path="device/qcom/common" name="CyanogenMod/android_device_qcom_common" revision="cm-12.1" />

    <!-- Hardware -->
    <project path="hardware/qcom/fm" name="CyanogenMod/android_hardware_qcom_fm" revision="cm-12.1" />
    <project path="external/mm-dash" name="CyanogenMod/android_external_mm-dash" revision="cm-12.1" />
    <project path="hardware/samsung" name="CyanogenMod/android_hardware_samsung" revision="cm-12.1" />

    <!-- CM replacements -->
    <project path="packages/services/Telecomm" name="TheWhisp/android_packages_services_Telecomm" revision="cm-12.1" />
</manifest>

```
