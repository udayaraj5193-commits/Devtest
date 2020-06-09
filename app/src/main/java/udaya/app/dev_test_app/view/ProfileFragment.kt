package udaya.app.dev_test_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import udaya.app.dev_test_app.R
import udaya.app.dev_test_app.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_profile,container,false)
        //get data and bind with ui
        binding.email.text = requireArguments().getString("userEmail").toString()
        binding.fName.text = requireArguments().getString("userFirstName").toString()
        binding.lName.text = requireArguments().getString("userLastName").toString()

        Glide.with(this)
            .load(requireArguments().getString("userAvatar").toString())
            .into(binding.profileimage)

        return binding.root
    }

}
