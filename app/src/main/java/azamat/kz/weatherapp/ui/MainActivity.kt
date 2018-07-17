package azamat.kz.weatherapp.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import azamat.kz.weatherapp.BaseActivity
import azamat.kz.weatherapp.R
import azamat.kz.weatherapp.local.entity.Weather
import azamat.kz.weatherapp.mvp.presenter.MainPresenter
import azamat.kz.weatherapp.mvp.view.IMainView
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : BaseActivity(), IMainView {


    private val mRvResult by lazy { rv_result }
    private lateinit var mEdSearch: AutoCompleteTextView
    private lateinit var mAdapter: WeatherRvAdapter

    var handler = Handler(Looper.getMainLooper())
    var workRunnable: Runnable? = null

    @InjectPresenter
    lateinit var mPresenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEd()
        initRv()

        if (!hasNetwork()) {
            mPresenter.getFromCache()
        }
    }

    private fun initRv() {
        mRvResult.layoutManager = LinearLayoutManager(this)
        mAdapter = WeatherRvAdapter()
        mRvResult.adapter = mAdapter
    }

    private fun initEd() {
        mEdSearch = ed_search
        mEdSearch.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                handler.removeCallbacks(workRunnable)
                workRunnable = Runnable {
                    mPresenter.getAutoComplete(s.toString())
                    mPresenter.getWeather(s.toString())
                }
                handler.postDelayed(workRunnable, 500)

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (workRunnable != null) {
                    handler.removeCallbacks(workRunnable)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })


    }

    private fun hasNetwork(): Boolean {
        val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = manager.activeNetworkInfo
        if (info != null) {
            if (info.isConnected) {
                return true
            }
        }
        return false
    }

//    region ===== IMainView ================================================================

    override fun setAutoComplete(list: ArrayList<String>) {
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list)
        mEdSearch.setAdapter(arrayAdapter)
        mEdSearch.showDropDown()
    }

    override fun setWeather(list: ArrayList<Weather>) {
        mAdapter.clear()
        mAdapter.addItems(list)
    }


//    endregion


}
