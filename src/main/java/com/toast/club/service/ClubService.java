package com.toast.club.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toast.club.integration.ClubDAO;
import com.toast.club.integration.ClubRoleDAO;
import com.toast.user.integration.UserDAO;
import com.toast.user.service.User;

@Service
public class ClubService {

	@Autowired
	private ClubDAO clubDAO;

	@Autowired
	private ClubRoleDAO clubRoleDAO;

	@Autowired
	private UserDAO userDAO;

	@Transactional
	public void save(Club club) {
		clubDAO.save(club);
	}

	@Transactional
	public List<Club> list() {
		return clubDAO.list();
	}

	@Transactional
	public Boolean addMember(Integer clubId, Integer memberId) {
		Club club = clubDAO.get(clubId);
		User user = userDAO.get(memberId);
		club.getClubMembers().add(user);
		return true;
	}

	@Transactional
	public void delete(Integer id) {
		clubDAO.delete(id);
	}

	@Transactional
	public Club get(Integer id) {
		return clubDAO.get(id);
	}

	@Transactional
	public List<ClubRole> getClubRoles() {
		return clubRoleDAO.list();
	}

	@Transactional
	public void saveSettings(Club club) {
		Club existingClub = clubDAO.get(club.getId());
		existingClub.setClubSettings(club.getClubSettings());
		clubDAO.save(existingClub);
	}

}
