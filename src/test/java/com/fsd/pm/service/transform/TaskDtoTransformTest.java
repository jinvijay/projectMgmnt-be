package com.fsd.pm.service.transform;

import static org.junit.Assert.assertThat;

import java.util.Date;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.fsd.pm.domain.Task;
import com.fsd.pm.service.dto.ProjectDto;
import com.fsd.pm.service.dto.TaskDto;
import com.fsd.pm.service.dto.UserDto;

public class TaskDtoTransformTest {

	TaskDtoTransform testObj = new TaskDtoTransform();

	@Test
	public void test() {
		TaskDto taskDto = new TaskDto();
		Date nowDate = new Date();

		taskDto.setEndDate(nowDate);
		taskDto.setParentTask(new TaskDto());
		taskDto.setPriority(1);
		taskDto.setProject(new ProjectDto());
		taskDto.setStartDate(nowDate);
		taskDto.setStatus("WIP");
		taskDto.setTask("TaskName");
		taskDto.setTaskId(1);
		taskDto.setUser(new UserDto());

		Task result = testObj.apply(taskDto);
		assertThat(result.getEndDate(), IsEqual.equalTo(nowDate));
		assertThat(result.getParentTask(), IsEqual.equalTo(null));
		assertThat(result.getProject(), IsEqual.equalTo(null));
		assertThat(result.getTask(), IsEqual.equalTo("TaskName"));
		assertThat(result.getTaskId(), IsEqual.equalTo(0));
	}

}
