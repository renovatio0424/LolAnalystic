package com.sample.renovatio.lolanalystic.Extension

import android.text.Editable

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)