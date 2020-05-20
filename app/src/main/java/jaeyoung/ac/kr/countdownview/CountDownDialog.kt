package jaeyoung.ac.kr.countdownview

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.view.Window
import kotlinx.android.synthetic.main.count_down_dialog.*

class CountDownDialog(context: Context,setCount : Int,isAnimation:Boolean) : Dialog(context) {
    private var startCount = setCount
    private var dialog : Dialog
    init {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.setContentView(R.layout.count_down_dialog)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.setCanceledOnTouchOutside(false)
        val handler = Handler()
        val animationHandler = Runnable {
            txt.animate()
                .alpha(1F)
                .scaleYBy(0.8F)
                .scaleXBy(0.8F)
                .scaleX(1.4f)
                .scaleY(1.4f)
                .setDuration(500).withEndAction {
                    txt.animate().alpha(0F).scaleX(0.8F).scaleY(0.8F).duration = 500
                }.start()
        }
        dialog = this
        val timerHandler = object : Runnable{
            override fun run() {
                txt.text = startCount.toString()
                startCount--
                if(startCount>=0){
                    if(isAnimation) handler.post(animationHandler)
                    handler.postDelayed(this,1000)
                }
                else dialog.dismiss()
            }
        }
        handler.post(timerHandler)
        this.show()
    }

}