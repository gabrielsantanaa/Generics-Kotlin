package com.gabrielsantana.projects.generics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.gabrielsantana.projects.generics.databinding.ActivityMainBinding
import com.gabrielsantana.projects.generics.databinding.AdapterGreenBinding
import com.gabrielsantana.projects.generics.databinding.AdapterYellowBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = BaseAdapter { parent ->
            GreenViewHolder(parent)
        }.apply {
            items = mutableListOf(
                User("gabrie", "admin@gmail.com"),
                User("ffgytyg", "klkklk@gmail.com"),
                User("sfsdfdsfds", "rrr@gmail.com"),
                User("dsfsdfd", "ddd@gmail.com"),
                User("dsffdsf", "fghghhgfh@gmail.com"),
                User("fdsfds", "hhhh@gmail.com"),
                User("hgfhfg", "fgh@gmail.com")
            )
        }
    }
}

class BaseAdapter <T: BaseViewHolder<U, I>, U, I: ViewBinding> (
    private val viewHolderLauncher: (ViewGroup) -> T
) : RecyclerView.Adapter<T>() {

    var items: MutableList<U> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return viewHolderLauncher(parent)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}

abstract class BaseViewHolder<U, I: ViewBinding>(val binding: I) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: U)
}

class GreenViewHolder(parent: ViewGroup) : BaseViewHolder<User, AdapterGreenBinding> (
    AdapterGreenBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )
) {

    override fun bind(item: User) {
        binding.textView.text = item.email
    }

}

class YellowViewHolder(parent: ViewGroup) : BaseViewHolder<User, AdapterYellowBinding> (
    AdapterYellowBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )
) {

    override fun bind(item: User) {
        binding.textView.text = item.email
    }

}

data class User(val username: String, val email: String)
