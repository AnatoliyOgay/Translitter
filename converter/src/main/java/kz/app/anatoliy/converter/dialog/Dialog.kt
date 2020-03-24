package kz.app.anatoliy.converter.dialog

import android.content.Context
import android.widget.LinearLayout
import android.widget.Toast
import umairayub.madialog.MaDialog

object Dialog {

    fun setDialog(context: Context, operation: () -> Unit){
    MaDialog.Builder(context)
        .setTitle("Удаление")
        .setMessage("Вы действительно хотите удалить все?")
        .setPositiveButtonText("Да")
        .setNegativeButtonText("Нет")
        .setButtonOrientation(LinearLayout.HORIZONTAL)
        .setPositiveButtonListener{
            operation()
            Toast.makeText(context, "Удалено", Toast.LENGTH_SHORT).show()
        }
        .setNegativeButtonListener {  }
        .build()
    }
}