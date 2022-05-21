package uz.gita.contactfirebase.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import uz.gita.contactfirebase.R
import uz.gita.contactfirebase.databinding.AddItemScreenBinding
import uz.gita.contactfirebase.db.UserDatabase
import uz.gita.contactfirebase.db.UserEntity
import uz.gita.contactfirebase.model.User

class AddUserScreen : Fragment() {
    private var _binding: AddItemScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = AddItemScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        val dao = UserDatabase.instance!!.getUserDao()
        binding.regBtn.setOnClickListener { view ->
            val email = binding.loginEdittext3.text.toString()
            val userName = binding.loginEdittext1.text.toString()
            val phone = binding.loginEdittext2.text.toString()
            if (!(email.isEmpty() || userName.isEmpty()||(!phone.contains("+998")&&phone.length!=13))) {
                auth.createUserWithEmailAndPassword(email, userName)
                    .addOnCompleteListener { result ->
                        if (result.isSuccessful) {
                            Snackbar.make(view, "adding user successfully", Snackbar.LENGTH_SHORT)
                                .show()
                            dao.insertUser(UserEntity(userName, phone, email))
                            findNavController().navigate(R.id.action_addUserScreen_to_homeScreen)
                        } else {
                            Snackbar.make(view, "error has been occurred!!!", Snackbar.LENGTH_SHORT)
                                .show()
                        }

                    }
                    .addOnFailureListener { exception ->
                        Snackbar.make(view, "${exception.message}", Snackbar.LENGTH_SHORT)
                            .show()
                    }
            }
            else if(phone.length!=13 && !phone.contains("+998")) {
                Snackbar.make(view, "Please enter phone format correctly!!!", Snackbar.LENGTH_SHORT)
                    .show()
            }

                else {
                Snackbar.make(view, "Please enter full info!!!", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }


        override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }


    }

