package udaya.app.dev_test_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import udaya.app.dev_test_app.R
import udaya.app.dev_test_app.data.api.ApiHelperImpl
import udaya.app.dev_test_app.data.api.ApiService
import udaya.app.dev_test_app.data.api.RetrofitBuilder
import udaya.app.dev_test_app.data.roomdb.Data
import udaya.app.dev_test_app.data.roomdb.DatabaseBuilder
import udaya.app.dev_test_app.data.roomdb.DatabaseHelperImpl
import udaya.app.dev_test_app.databinding.FragmentUserListBinding
import udaya.app.dev_test_app.utils.NetworkConnectionInterceptor
import udaya.app.dev_test_app.utils.Status
import udaya.app.dev_test_app.utils.UserViewModelFactory
import udaya.app.dev_test_app.view.adapter.UserAdapter
import udaya.app.dev_test_app.viewmodel.UserViewModel


class UserListFragment : Fragment() {

    lateinit var viewModel: UserViewModel
    lateinit var binding: FragmentUserListBinding
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater,
            R.layout.fragment_user_list,container,false)
        setupViewModel()
        setupObserver()
        return binding.root
    }

    private fun setupViewModel() {
        val networkConnectionInterceptor= NetworkConnectionInterceptor(requireContext())
        val apiService: ApiService = RetrofitBuilder
            .getRetrofit(networkConnectionInterceptor)
            .create(ApiService::class.java)

        viewModel = ViewModelProviders.of(this,
            UserViewModelFactory(ApiHelperImpl(apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(requireContext())))).get(UserViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.getUsers().observe(this, Observer {
            when (it.status) {

                Status.SUCCESS -> {
                    binding.progressBar.visibility=View.GONE
                    it.data?.let { users ->
                        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
                        binding.recyclerview.addItemDecoration(DividerItemDecoration(binding.recyclerview.context,(binding.recyclerview.layoutManager as LinearLayoutManager).orientation)
                        )
                        binding.recyclerview.adapter = UserAdapter(users){
                             clickItem : Data -> itemClick(clickItem)
                        }
                        binding.recyclerviewCount.text=users.size.toString()
                    }
                    binding.recyclerview.visibility=View.VISIBLE
                }

                Status.LOADING -> {
                    binding.recyclerview.visibility=View.GONE
                    binding.progressBar.visibility=View.VISIBLE
                }

                Status.ERROR -> {
                    binding.recyclerview.visibility=View.VISIBLE
                    binding.progressBar.visibility=View.GONE
                }
            }
        })
    }

//pass the values to profile fragment
    private fun itemClick(userData : Data) {
        val bundle : Bundle= bundleOf(
            "userEmail" to userData.email,
            "userFirstName" to userData.first_name,
            "userLastName" to userData.last_name,
            "userAvatar" to userData.avatar
            )
        findNavController().navigate(R.id.action_userListFragment_to_profileFragment,bundle)
    }

}
