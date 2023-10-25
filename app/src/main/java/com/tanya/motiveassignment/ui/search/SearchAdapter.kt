package com.tanya.motiveassignment.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tanya.motiveassignment.data.model.City
import com.tanya.motiveassignment.databinding.SearchItemLayoutBinding

class SearchAdapter: PagingDataAdapter<City,SearchAdapter.FeatureViewHolder>(COMPARATOR) {
    class FeatureViewHolder(private val binding : SearchItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item : City){
            binding.cityName.text = item.city
            binding.stateName.text = item.state
            binding.countryName.text = item.country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        return FeatureViewHolder(
            SearchItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent , false))
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        val city = getItem(position)
        if (city != null) {
            holder.bind(city)
        }    }



  /*  fun replaceData(list: List<City>) {
        searchList.clear()
        searchList.addAll(list)
    }

    fun clear(){
        if(searchList.isNotEmpty()) {
            searchList.clear()
            notifyDataSetChanged()
        }
    }*/
    companion object{
        private val COMPARATOR = object: DiffUtil.ItemCallback<City>(){

            override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
                return oldItem.city == newItem.city
            }

            override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
                return oldItem == newItem
            }
        }
    }
}