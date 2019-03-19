
#include "Camera.hpp"
#include "glm/glm.hpp"//core glm functionality
#include "glm/gtc/matrix_transform.hpp"//glm extension for generating common transformation matrices
#include "glm/gtc/matrix_inverse.hpp"
#include "glm/gtc/type_ptr.hpp"
#include "GLEW/glew.h"
#include "GLFW/glfw3.h"


namespace gps {

	Camera::Camera(glm::vec3 cameraPosition, glm::vec3 cameraTarget)
	{
		this->cameraPosition = cameraPosition;
		this->cameraTarget = cameraTarget;
		this->cameraDirection = glm::normalize(cameraTarget - cameraPosition);
		this->cameraRightDirection = glm::normalize(glm::cross(this->cameraDirection, glm::vec3(0.0f, 1.0f, 0.0f)));
		up = glm::vec3(0.0f, 1.0f, 0.0f);

	}
	
	void Camera::set(glm::vec3 newCamPos) {
		this->cameraPosition = newCamPos;
	}

	glm::vec3 Camera::getPosition() {
		return this->cameraPosition;
	}

    glm::mat4 Camera::getViewMatrix()
    {
		return glm::lookAt(cameraPosition, cameraPosition + cameraTarget , up);
    }

	glm::vec3 Camera::getCameraTarget()
	{
		return cameraTarget;
	}
    
    void Camera::move(MOVE_DIRECTION direction, float speed)
    {
        switch (direction) {
            case MOVE_FORWARD:
				if((cameraPosition + cameraTarget * speed).y > -0.8f && (cameraPosition + cameraTarget * speed).y < 3.0f)
					if ((cameraPosition + cameraTarget * speed).x > -8.0f && (cameraPosition + cameraTarget * speed).x < 10.0f && (cameraPosition + cameraTarget * speed).z > -8.0f && (cameraPosition + cameraTarget * speed).z < 8.0f)
						cameraPosition += cameraTarget * speed;
                break;
                
            case MOVE_BACKWARD:
				if ((cameraPosition - cameraTarget * speed).y > -0.8f && (cameraPosition - cameraTarget * speed).y < 3.0f)
					if ((cameraPosition - cameraTarget * speed).x > -8.0f && (cameraPosition - cameraTarget * speed).x < 10.0f && (cameraPosition - cameraTarget * speed).z > -8.0f && (cameraPosition - cameraTarget * speed).z < 8.0f)
						cameraPosition -= cameraTarget * speed;
                break;
                
            case MOVE_RIGHT:
				if ((cameraPosition + cameraRightDirection * speed).x > -8.0f && (cameraPosition + cameraRightDirection * speed).x < 10.0f && (cameraPosition + cameraRightDirection * speed).z > -8.0f && (cameraPosition + cameraRightDirection * speed).z < 8.0f)
					cameraPosition += cameraRightDirection * speed;
                break;
                
            case MOVE_LEFT:
				if ((cameraPosition - cameraRightDirection * speed).x > -8.0f && (cameraPosition - cameraRightDirection * speed).x < 10.0f && (cameraPosition - cameraRightDirection * speed).z > -8.0f && (cameraPosition - cameraRightDirection * speed).z < 8.0f)
					cameraPosition -= cameraRightDirection * speed;
                break;
        }
    }
    
    void Camera::rotate(float pitch, float yaw)
    {
		glm::vec3 direction;
		direction.x = cos(glm::radians(pitch)) * cos(glm::radians(yaw));
		direction.y = sin(glm::radians(pitch));
		direction.z = cos(glm::radians(pitch)) * sin(glm::radians(yaw));

		this->cameraTarget = glm::normalize(direction);
		this->cameraRightDirection = glm::normalize(glm::cross(this->cameraTarget, glm::vec3(0.0f, 1.0f, 0.0f)));
		this->up = glm::normalize(glm::cross(cameraRightDirection, cameraTarget));
    }
}
