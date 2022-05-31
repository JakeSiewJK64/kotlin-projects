package au.swin.joekanesiew_extension_bookstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class BookshopAdapter (private val bookList: ArrayList<Book>) : RecyclerView.Adapter<BookshopAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.bookviewmodel, parent, false) as View
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = bookList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    inner class BookViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val mBookTitle: TextView = view.findViewById(R.id.bookTitle)
        private val mBookRating: RatingBar = view.findViewById(R.id.bookRating)
        private val mBookImage: ImageView = view.findViewById(R.id.bookCover)
        fun bind(item: Book) {
            val id = view.resources.getIdentifier(item.image, null, null)
            mBookTitle.text = item.bookName
            mBookImage.setImageResource(id)
            mBookRating.rating = item.bookRating
        }
    }
}