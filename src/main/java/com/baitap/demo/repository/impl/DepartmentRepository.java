package com.baitap.demo.repository.impl;

import com.baitap.demo.modal.Department;
import com.baitap.demo.modal.Employee;
import com.baitap.demo.repository.IDepartmentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentRepository implements IDepartmentRepository {

	@Override
	public List<Department> findAll() {
		Session session = ConnectionUtil.sessionFactory.openSession(); // Bước 1: Mở phiên làm việc (Session) từ ConnectionUtil
		List<Department> departments = null;
		try {
			departments = session.createQuery("FROM Department").getResultList(); // Bước 2: Sử dụng HQL để lấy danh sách sinh viên
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close(); // Bước 3: Đóng phiên làm việc sau khi lấy danh sách xong
		}
		return departments;

	}

	@Override
	public Department findById(long id) {
		Session session = ConnectionUtil.sessionFactory.openSession();
		Department department = null;
		try {
			department = (Department) session.createQuery("from Department d where d.id = :id")
					.setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return department;
	}

	@Override
	public Department save(Department department) {
		try (Session session = ConnectionUtil.sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();


			try {

				session.saveOrUpdate(department);

				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback(); // Rollback nếu có lỗi
				}
				throw new RuntimeException(e);
			}
		}
		return department;
	}

	@Override
	public void delete(int id) {
		Session session = ConnectionUtil.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.createQuery("DELETE FROM Department d WHERE d.id = :id")
				.setParameter("id", id)
				.executeUpdate();
		transaction.commit();
	}
}




