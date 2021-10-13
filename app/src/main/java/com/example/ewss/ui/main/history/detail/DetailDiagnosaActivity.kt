package com.example.ewss.ui.main.history.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.core.domain.model.HistoryDiagnosa
import com.example.ewss.R
import com.example.ewss.databinding.ActivityDetailDiagnosaBinding
import com.example.ewss.utils.StatusPatient.checkStatusColor

class DetailDiagnosaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDiagnosaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDiagnosaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        val diagnosa = intent.getParcelableExtra<HistoryDiagnosa>(GET_DETAIL_DIAGNOSA)

        if (diagnosa != null) setUI(diagnosa)
    }

    private fun initialize() {
        with(binding.diagnosa) {
            btnDiagnosa.visibility = View.GONE
            fullname.isEnabled = false
            address.isEnabled = false
            age.isEnabled = false
            kesadaran.isEnabled = false
            pernafasan.isEnabled = false
            nadi.isEnabled = false
            tekananDarah.isEnabled = false
            suhu.isEnabled = false
            root.setBackgroundResource(0)
        }
    }

    private fun setUI(diagnosaHistory: HistoryDiagnosa) {
        with(binding.diagnosa) {
            inputFullname.setText(diagnosaHistory.nama)
            inputAddress.setText(diagnosaHistory.alamat)
            inputAge.setText(diagnosaHistory.usia.toString())
            kesadaranAutocomplete.setText(diagnosaHistory.tingkatKesadaran)
            inputPernafasan.setText(diagnosaHistory.pernafasan.toString())
            inputNadi.setText(diagnosaHistory.denyutNadi.toString())
            inputTekananDarah.setText(diagnosaHistory.tekananDarah.toString())
            inputSuhu.setText(diagnosaHistory.suhu.toString())
        }

        with(binding) {
            val diagnosaResult = diagnosaHistory.diagnosaResult
            if (diagnosaResult != null) {
                status.text = getString(R.string.status, diagnosaResult.result)
                ketaranganHasil.text = getString(
                    R.string.keterangan_detail,
                    diagnosaResult.detailResult
                )
                label.background.setTintList(
                    ContextCompat.getColorStateList(
                        this@DetailDiagnosaActivity,
                        checkStatusColor(diagnosaResult.result)
                    )
                )
            }
        }
    }

    companion object {
        const val GET_DETAIL_DIAGNOSA = "GET_DETAIL_DIAGNOSA"
    }
}