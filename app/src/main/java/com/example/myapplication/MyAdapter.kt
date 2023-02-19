package com.example.myapplication
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.Articles
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


class MyAdapter(var context: Context, items: Array<Articles>) :
    RecyclerView.Adapter<MyViewHolder>() {
    var items: Array<Articles>
    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

       //items[position].getSnippet()!!.getChannelId()
        val url=items[position].urlToImage
        val title=items[position].title
        val description=items[position].description
        val content=items[position].content

        Picasso.get().load(items[position].urlToImage).into(holder.imageView)
        holder.time.text=convert(items[position].publishedAt!!)
        holder.channelName.text=items[position].getSource()!!.getName()
        holder.textView1.text=items[position].title

        holder.ll.setOnClickListener {
            val i= Intent(context,NewsActivity::class.java)

            i.putExtra("img_url",url)
            i.putExtra("title",title)
            i.putExtra("description",description)
            i.putExtra("content",content)
            context.startActivity(i)

        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun convert(publishedDay: String): String {
        val formatPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val publishedAt = LocalDateTime.parse(publishedDay, formatPattern)
        val currentDate = LocalDateTime.now().withNano(0)

        val differenceInSeconds = ChronoUnit.SECONDS.between(publishedAt, currentDate)
        val differenceInDays = ChronoUnit.DAYS.between(publishedAt, currentDate)
        val differenceInMonths = ChronoUnit.MONTHS.between(publishedAt, currentDate)
        val differenceInYears=ChronoUnit.YEARS.between(publishedAt, currentDate)
        return findDifference(differenceInSeconds, differenceInDays, differenceInMonths,differenceInYears)
    }

    private fun findDifference(differenceInSeconds: Long, differenceInDays: Long, differenceInMonths: Long, differenceInYears:Long): String {
        val hours = differenceInSeconds / 3600
        when(differenceInDays) {
            in 21..31 -> {
                return "3 weeks ago"
            }
            in 14..20 -> {
                return "2 weeks ago"
            }
            in 2..13 -> {
                return "$differenceInDays days ago"
            }
            in 0..1 -> {
                return "$hours hours ago"
            }
        }
        if (differenceInMonths in 0..11) {
            return "$differenceInMonths month ago"
        }
        return "$differenceInYears years ago"
    }




    override fun getItemCount(): Int {
        return items.size
    }


}
