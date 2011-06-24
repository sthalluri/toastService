ALTER TABLE meetingRole
{
  DROP CONSTRAINT IF EXISTS userRole,
  ADD CONSTRAINT userRole
  {
    FOREIGN KEY (userId)
    REFERENCES user (id)
    ON DELETE SET NULL
    ON UPDATE NO ACTION
  },  
  DROP INDEX IF EXISTS userRole,
  ADD INDEX userRole (userId ASC) 
};