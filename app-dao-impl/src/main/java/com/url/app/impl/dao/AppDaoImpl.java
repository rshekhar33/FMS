package com.url.app.impl.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.url.app.dto.entity.Action;
import com.url.app.dto.entity.UrlRolesBean;
import com.url.app.dto.entity.User;
import com.url.app.dto.entity.UserMng;
import com.url.app.dto.entity.UserSkillsetMng;
import com.url.app.interf.dao.AppDao;
import com.url.app.utility.AppCommon;
import com.url.app.utility.AppSQL;

/**
 * Dao implementation of application.
 * Database related method implementations.
 * 
 * @author Shekhar Shinde
 */
@Repository(value = "appDaoImpl")
public class AppDaoImpl implements AppDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<UrlRolesBean> fetchUrlRoleIds() {
		final TypedQuery<UrlRolesBean> typedQuery = entityManager.createQuery(AppSQL.QRY_SELECT_URL_ROLE_ID, UrlRolesBean.class);

		return typedQuery.getResultList();
	}

	@Override
	public List<Action> fetchActions() {
		final TypedQuery<Action> typedQuery = entityManager.createQuery(AppSQL.QRY_SELECT_ACTIONS, Action.class);

		return typedQuery.getResultList();
	}

	@Override
	public User fetchUser(final String userName) {
		final TypedQuery<User> typedQuery = entityManager.createQuery(AppSQL.QRY_SELECT_USER_ROLES_BY_USERNAME, User.class);
		typedQuery.setParameter(AppSQL.PARAMETER_USER_NAME, userName);

		User user = null;
		final List<User> queryResult = typedQuery.getResultList();
		if (!queryResult.isEmpty()) {
			user = queryResult.get(0);
		}

		return user;
	}

	@Override
	public int userUpdateLastLoginSuccess(final Integer userId) {
		final Query query = entityManager.createQuery(AppSQL.QRY_UPDATE_USER_LAST_SUCCESS_LOGIN_DATE);
		query.setParameter("failedAttemptCnt", 0);
		query.setParameter("lastSuccessfulLoginDate", AppCommon.currentDateTime());
		query.setParameter(AppSQL.PARAMETER_USER_ID, userId);

		return query.executeUpdate();
	}

	@Override
	public int userUpdateLastLoginFailure(final String userName) {
		final Query query = entityManager.createQuery(AppSQL.QRY_UPDATE_USER_LAST_FAILED_LOGIN_DATE);
		query.setParameter("lastFailedLoginDate", AppCommon.currentDateTime());
		query.setParameter(AppSQL.PARAMETER_USER_NAME, userName);

		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMng> fetchUsersListing() {
		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery(AppSQL.PROC_ALL_USERS, UserMng.class);

		return query.getResultList();
	}

	@Override
	public User fetchUserWithRoles(final Integer userId) {
		final TypedQuery<User> typedQuery = entityManager.createQuery(AppSQL.QRY_SELECT_USER_ROLES_BY_USERID, User.class);
		typedQuery.setParameter(AppSQL.PARAMETER_USER_ID, userId);

		User user = null;
		final List<User> queryResult = typedQuery.getResultList();
		if (!queryResult.isEmpty()) {
			user = queryResult.get(0);
		}

		return user;
	}

	@Override
	public User fetchUserWithModules(final Integer userId) {
		final TypedQuery<User> typedQuery = entityManager.createQuery(AppSQL.QRY_SELECT_USER_SKILLSETS_BY_USERID, User.class);
		typedQuery.setParameter(AppSQL.PARAMETER_USER_ID, userId);

		User user = null;
		final List<User> queryResult = typedQuery.getResultList();
		if (!queryResult.isEmpty()) {
			user = queryResult.get(0);
		}

		return user;
	}

	@Override
	public String generateNewCode(final String commonSettingsType) {
		final TypedQuery<String> typedQuery = entityManager.createQuery(AppSQL.QRY_SELECT_COMMON_SETTING_VALUE, String.class);
		typedQuery.setParameter(AppSQL.PARAMETER_TYPE, commonSettingsType);

		final String value = typedQuery.getSingleResult();

		final Query query = entityManager.createQuery(AppSQL.QRY_UPDATE_COMMON_SETTING_VALUE);
		query.setParameter(AppSQL.PARAMETER_TYPE, commonSettingsType);

		query.executeUpdate();

		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserSkillsetMng> fetchUserSkillsetsListing() {
		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery(AppSQL.PROC_USER_SKILLSETS, UserSkillsetMng.class);

		return query.getResultList();
	}

	@Override
	public int userSkillsetsUpdateIsActive(final User user) {
		final Query query = entityManager.createQuery(AppSQL.QRY_UPDATE_USER_SKILLSETS_IS_ACTIVE);
		query.setParameter("isActive", user.getIsActive());
		query.setParameter("modifiedBy", user.getModifiedBy());
		query.setParameter("modifiedDate", AppCommon.currentDateTime());
		query.setParameter(AppSQL.PARAMETER_USER_ID, user.getUserId());

		return query.executeUpdate();
	}
}