package com.oscarescamilla.com.ui.Adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.oscarescamilla.com.data.model.comment.Comment
import com.oscarescamilla.com.data.model.post.Post
import com.oscarescamilla.com.data.model.user.User

class ExpandableListAdapter(
    private val context: Context,
    private val itemsPost: List<Post>,
    private val itemsComments: HashMap<Post,Comment>,
    private val user: User
): BaseExpandableListAdapter() {


    override fun getGroupCount(): Int {
        return  itemsPost.size
    }

    override fun getChildrenCount(p0: Int): Int {
        return  1
    }

    override fun getGroup(position: Int): Any {
        return itemsPost[position]
    }

    override fun getChild(p0: Int, p1: Int): Comment? {
        return itemsComments.get(itemsPost.get(p0))
    }

    override fun getGroupId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        TODO("Not yet implemented")
    }

    override fun hasStableIds(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, parent: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        TODO("Not yet implemented")
    }
}