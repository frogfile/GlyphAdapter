//
// Copyright (C) 2025 The LineageOS Project
//
// SPDX-License-Identifier: Apache-2.0
//

package com.nothing.thirdparty;

interface IGlyphService {
    void setFrameColors(in int[] iArray);
    void openSession();
    void closeSession();
    boolean register(in String str);
    boolean registerSDK(in String str1, in String str2);
}