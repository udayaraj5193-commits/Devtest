package udaya.app.dev_test_app.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import udaya.app.dev_test_app.R
import udaya.app.dev_test_app.data.roomdb.Data
import udaya.app.dev_test_app.databinding.ItemLayoutBinding

class UserAdapter(
     private val users: List<Data>,
     private val clickListener: (Data) -> Unit
) : RecyclerView.Adapter<UserAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Data,clickListener: (Data) -> Unit) {

            binding.email.text=user.email
            binding.itemCardView.setOnClickListener { clickListener(user) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :DataViewHolder{
        val layoutInflater =LayoutInflater.from(parent.context)
        val binding : ItemLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_layout,parent,false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position],clickListener)



}