package uz.gita.contactfirebase.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.gita.contactfirebase.R
import uz.gita.contactfirebase.adapter.UserAdapter
import uz.gita.contactfirebase.databinding.HomeScreenBinding
import uz.gita.contactfirebase.db.UserDatabase
import uz.gita.contactfirebase.db.UserEntity
import uz.gita.contactfirebase.model.User
import uz.gita.contactfirebase.ui.viewmodels.HomeScreenViewModel


class HomeScreen : Fragment() {
    private var _binding: HomeScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeScreenViewModel by viewModels()
    private lateinit var adapter: UserAdapter
    private lateinit var  liveData:LiveData<List<UserEntity>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = HomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = UserAdapter()
        liveData = viewModel.getUserList(requireContext())
        liveData.observe(viewLifecycleOwner,observer)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.plusBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_addUserScreen)
        }


    }


    private val observer = Observer<List<UserEntity>> {
        adapter.differ.submitList(it)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}