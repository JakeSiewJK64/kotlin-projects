package au.swin.firestoreinitdb.models

data class mUserModel(
    var mUserName: String?,
    var mUserPassword: String?,
    var mUserRole: Number?
)

data class mUserModelRepository(
    var userModelList: ArrayList<mUserModel> = arrayListOf()
)
