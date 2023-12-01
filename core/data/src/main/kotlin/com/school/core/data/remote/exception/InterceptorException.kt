package com.school.core.data.remote.exception

import okio.IOException

class NeedSignInException : IOException()
class ExpiredTokenException : IOException()
class FailRefreshException : IOException()