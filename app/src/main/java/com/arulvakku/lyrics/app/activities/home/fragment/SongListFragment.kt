package com.arulvakku.lyrics.app.activities.home.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.activities.home.adapter.TitlesAdapter
import com.arulvakku.lyrics.app.activities.lyrics.LyricsActivity
import com.arulvakku.lyrics.app.data.Song
import com.arulvakku.lyrics.app.databinding.FragmentSongListBinding
import com.arulvakku.lyrics.app.utilities.getSongList
import com.sembiyan.songs.app.listeners.RecyclerOnRowItemClickListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SongListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SongListFragment : Fragment(), RecyclerOnRowItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    var binding: FragmentSongListBinding? = null
    lateinit var mSongTitlesAdapter: TitlesAdapter
    private lateinit var titles: List<Song>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSongListBinding.bind(view)
        setAdapter();
    }

    private fun setAdapter() {
        titles = context?.let { getSongList(it) }!!

        mSongTitlesAdapter = TitlesAdapter(
            context!!,
            titles.sortedBy { it.sSong.toString() },
            this@SongListFragment
        )
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.adapter = mSongTitlesAdapter

    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SongListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



    override fun onItemRowListener(position: Int, song: Song) {
        val intent = Intent(activity, LyricsActivity::class.java)
        intent.putExtra("position", song.sSongId)
        intent.putExtra("category_name", song.sCategory)
        intent.putExtra("title", song.sTitle)
        intent.putExtra("song", song.sSong)
        startActivity(intent)
    }
}