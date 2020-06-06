package com.example.pandemicstrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pandemicstrivia.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), CategoryAdapter.OnCategoryItemClickListener  {

    private val viewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val categoryList = viewModel.generateCategoryList
        binding.categoryList.adapter = CategoryAdapter(categoryList, this)
        binding.categoryList.layoutManager = LinearLayoutManager(context)
        binding.categoryList.setHasFixedSize(true)
        return binding.root
    }



    override fun onCategoryClick(category: CategoryItem, position: Int, view: View) {
        Toast.makeText(context, "Category Clicked ${category.name} ID: ${category.id}", Toast.LENGTH_SHORT).show()
        viewModel.setupGame(category.id)
        view.findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }

}
