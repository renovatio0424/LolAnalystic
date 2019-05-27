package com.sample.renovatio.analytics.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.sample.renovatio.analytics.model.DataModel.SummonerDTO
import com.sample.renovatio.analytics.R
import com.sample.renovatio.analytics.match_list.MatchListActivity
import com.sample.renovatio.analytics.util.extension.showSnackbar
import com.sample.renovatio.analytics.util.extension.toHttpErrorString
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainContract.View {

    companion object {
        const val REQUEST_APP_UPDATE = 1003
    }

    private val presenter: MainContract.Presenter by inject { parametersOf(this) }
    private var summonerData: SummonerDTO? = null

    private val loadingDialog: AppCompatDialog by lazy {
        AlertDialog.Builder(this)
            .setView(R.layout.dialog_loading)
            .setCancelable(false)
            .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateIfRequired()

        main_activity_search_button.setOnClickListener {
            presenter.getSummonerData(main_activity_search_editext_view?.text.toString())
        }

        main_activity_search_text_view.setOnClickListener {
            moveToMyMatchListActivity()
        }
    }

    private fun moveToMyMatchListActivity() {
        if (summonerData == null)
            showSnackbar(R.string.error_search)
        else
            MatchListActivity.show(this, summonerData!!.accountId)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_APP_UPDATE) {
            when (resultCode) {
                //case 1 : user has accepted the update. For immediate updates, you might not receive this callback
                Activity.RESULT_OK -> showSnackbar("In App Update Success!!")
                //case 2 : user has denied or cancelled the update
                Activity.RESULT_CANCELED -> showSnackbar("In App Update Canceled")
                //case 3 : some other error prevented either the user from providing consent or the update to proceed
                ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> showSnackbar("In App Update Failed")
            }
        }
    }

    private fun updateIfRequired() {
        val appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateTask = appUpdateManager.appUpdateInfo

        appUpdateTask.addOnSuccessListener { appUpdateInfo ->
            // For a flexible update use AppUpdateType.FLEXIBLE
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                //request Update
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.IMMEDIATE/*AppUpdateType.FLEXIBLE*/,
                    this,
                    REQUEST_APP_UPDATE
                )
            }
        }
    }

    override fun showLoadingDialog() {
        if (!loadingDialog.isShowing)
            loadingDialog.show()
    }

    override fun dismissLoadingDialog() {
        if (loadingDialog.isShowing)
            loadingDialog.dismiss()
    }

    override fun showErrorMessage(errorCode: Int) {
        showSnackbar(errorCode.toHttpErrorString())
    }

    override fun showSummonerDataOnResultText(summonerData: SummonerDTO) {
        main_activity_search_text_view?.text = summonerData.toString()
    }

    override fun onStop() {
        super.onStop()
        //언제 clear 해야 할까?
        presenter.unsubscribe()
    }


}