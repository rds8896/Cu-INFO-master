package com.skeleton.util.facebookutil;

import com.facebook.FacebookException;


/**
 *  +++++++++++++++++++++++++++++++
 *  +++++++++Click labs +++++++++++
 *  +++++++++++++++++++++++++++++++
 */
public interface FacebookResponseHandler {

    /**
     * On success.
     *
     * @param fbUserDetails the fb user details
     */
    void onSuccess(SocialUserDetails fbUserDetails);

    /**
     * On cancel.
     *
     * @param msg the msg
     */
    void onCancel(String msg);

    /**
     * On error.
     *
     * @param e the e
     */
    void onError(FacebookException e);
}
