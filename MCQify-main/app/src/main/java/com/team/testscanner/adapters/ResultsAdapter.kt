package com.team.testscanner.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.team.testscanner.R
import com.team.testscanner.models.Quiz
import com.team.testscanner.ui.activities.AnalysisActivity

class ResultsAdapter(val context : Context, private val quiz:MutableList<Quiz>):RecyclerView.Adapter<ResultsAdapter.MyViewHolder>() {


    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textTitle: TextView =itemView.findViewById(R.id.test_title_text_preview)
        val textScore: TextView =itemView.findViewById(R.id.test_score)
        val resultsButton : Button = itemView.findViewById(R.id.result_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.results_recyclerview,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=quiz[position]
        val score=currentItem.score
        var red = false
        holder.textScore.text= "Your Score : $score"
        holder.textScore.setTextColor(Color.BLACK)
        holder.textTitle.text=currentItem.title
        holder.resultsButton.text="Analysis"
        if(!currentItem.isKeyAvailable){
            holder.textScore.setTextColor(Color.RED)
            holder.textScore.text="Please Add answer key"
            holder.resultsButton.text="Add Key"
            red=true
        }
        holder.resultsButton.setOnClickListener {
            val intent = Intent(context,AnalysisActivity::class.java)
            intent.putExtra("id",currentItem.id)
            if(red==true){
                intent.putExtra("mode","update")
            }
            else{
                intent.putExtra("mode","get")
            }
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return quiz.size
    }
}