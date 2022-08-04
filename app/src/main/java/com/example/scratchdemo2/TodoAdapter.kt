package com.example.scratchdemo2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scratchdemo2.databinding.ActivityMain2Binding
import kotlinx.android.synthetic.main.activity_task2.*
import kotlinx.android.synthetic.main.item_todo.view.*
import java.text.SimpleDateFormat
import java.util.*


class TodoAdapter (val list : List<TodoModel>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    inner class TodoViewHolder(val binding: ActivityMain2Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todoModel: TodoModel){
            with(itemView){
                val colors = resources.getIntArray(R.array.random_colors)
                val randomColor = colors[Random().nextInt(colors.size)]
                viewColor.setBackgroundColor(randomColor)
                titleTv.text = todoModel.title
                subtitleTv.text = todoModel.description
                updateTime(todoModel.time)
                updateDate(todoModel.date)



            }

        }
        private fun updateTime(time: Long) {
            val myformat ="h:mm a"
            val sdf = SimpleDateFormat(myformat)
            itemView.ShowTimeTv.text= sdf.format(Date(time))
        }


        private fun updateDate(date :Long){
            val myformat ="EEE, d MMM yyyy"
            val sdf = SimpleDateFormat(myformat)
            itemView.ShowDateTv.text = sdf.format(Date(date))

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ActivityMain2Binding.inflate(LayoutInflater.from(parent.context), parent,false ))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(list[position]) //kisse bind karte hain ? --> list ki postion se views ko
    }

    override fun getItemCount(): Int {
        return list.size
    }




}