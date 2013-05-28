package org.springframework.security.oauth2.provider.approval;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

/**
 * Basic interface for determining whether a given client authentication request has been approved by the current user.
 * 
 * @author Ryan Heaton
 * @author Dave Syer
 * @author Amanda Anganes
 */
public interface UserApprovalHandler {


	/**
	 * <p>
	 * Tests whether the specified authorization request has been approved by the current user (if there is one).
	 * </p>
	 * 
	 * @param oAuth2Request the authorization request.
	 * @param userAuthentication the user authentication for the current user.
	 * @return true if the request has been approved, false otherwise
	 */
	boolean isApproved(OAuth2Request oAuth2Request, Authentication userAuthentication);

	/**
	 * <p>
	 * Provides a hook for allowing requests to be pre-approved (skipping the User Approval Page). Some implementations
	 * may allow users to store approval decisions so that they only have to approve a site once. This method is called
	 * in the AuthorizationEndpoint before sending the user to the Approval page. If this method sets oAuth2Request.approved
	 * to true, the Approval page will be skipped.
	 * </p>
	 * 
	 * @param oAuth2Request the authorization request.
	 * @param userAuthentication the user authentication
	 * @return the OAuth2Request, modified if necessary
	 */
	OAuth2Request checkForPreApproval(OAuth2Request oAuth2Request, Authentication userAuthentication);

	/**
	 * <p>
	 * Provides an opportunity to update the authorization request before it is checked for approval in cases where the
	 * incoming approval parameters contain richer information than just true/false (e.g. some scopes are approved, and
	 * others are rejected), implementations may need to be able to modify the {@link OAuth2Request} before a
	 * token is generated from it.
	 * </p>
	 * 
	 * @param oAuth2Request the authorization request.
	 * @param userAuthentication the user authentication
	 * @return the OAuth2Request, modified if necessary
	 */
	OAuth2Request updateAfterApproval(OAuth2Request oAuth2Request, Authentication userAuthentication);
}
