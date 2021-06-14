#
# Copyright (C) 2020 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)

# Inherit from picasso device
$(call inherit-product, device/xiaomi/picasso/device.mk)

# Inherit some common Arrow stuff.
$(call inherit-product, vendor/havoc/config/common_full_phone.mk)

#Havoc
HAVOC_MAINTAINER := Dinh San (@san2k)
HAVOC_GROUP_URL := https://t.me/K305G_VN
WITH_GAPPS := true

# Device identifier. This must come after all inclusions.
PRODUCT_NAME := havoc_picasso
PRODUCT_DEVICE := picasso
PRODUCT_BRAND := Xiaomi
PRODUCT_MODEL := Redmi K30 5G
PRODUCT_MANUFACTURER := Xiaomi

PRODUCT_CHARACTERISTICS := nosdcard

PRODUCT_GMS_CLIENTID_BASE := android-xiaomi
